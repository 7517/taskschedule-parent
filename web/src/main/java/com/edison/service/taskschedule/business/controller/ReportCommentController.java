package com.edison.service.taskschedule.business.controller;

import com.alibaba.fastjson.JSONArray;
import com.edison.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.edison.saas.common.framework.exception.ResourceNotFoundException;
import com.edison.saas.common.framework.spring.DateConverter;
import com.edison.saas.common.framework.web.ExcelUtil;
import com.edison.saas.common.framework.web.controller.PageContent;
import com.edison.saas.common.framework.web.data.PageRequest;
import com.edison.saas.common.framework.web.data.PageRequestConvert;
import com.edison.saas.common.framework.web.data.PageSearchRequest;
import com.edison.service.report.business.report.dto.ReportCommentAddDto;
import com.edison.service.report.business.report.dto.ReportCommentCondition;
import com.edison.service.report.business.report.dto.ReportCommentEditDto;
import com.edison.service.report.business.report.vo.ReportCommentVO;
import com.edison.service.taskschedule.business.report.domain.ReportComment;
import com.edison.service.taskschedule.business.report.service.ReportCommentService;
import com.edison.service.taskschedule.report.report.valid.ReportCommentValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理报告评论
 * @author icode
 */
@Api(description = "报告评论", tags = "ReportComment")
@RestController
@RequestMapping(value = "/report/report/reportcomment")
public class ReportCommentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportCommentController.class);


	@Autowired
	private ReportCommentService reportCommentService;



	@Autowired
	private ReportCommentValidator reportCommentValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(reportCommentValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}


  
	/**
	 * 新增报告评论
	 * @param reportCommentAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增报告评论", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "report.report.reportcomment.add")
	public ReportCommentVO add(@RequestBody @Valid ReportCommentAddDto reportCommentAddDto){
		ReportComment reportComment = new ReportComment();
		BeanUtils.copyProperties(reportCommentAddDto, reportComment);

		reportCommentService.add(reportComment);

		return  initViewProperty(reportComment);
	}

	/**
	 * 删除报告评论,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除报告评论", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "report.report.reportcomment.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete reportComment :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			reportCommentService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新报告评论
	 * @param reportCommentEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产报告评论(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "report.report.reportcomment.update")
	public ReportCommentVO update(@RequestBody @Valid ReportCommentEditDto reportCommentEditDto, @PathVariable Long id){
		ReportComment reportComment = new ReportComment();
		BeanUtils.copyProperties(reportCommentEditDto, reportComment);
		reportComment.setId(id);
		reportCommentService.merge(reportComment);

		ReportCommentVO vo = initViewProperty(reportComment);
		return  vo;
	}

	/**
	 * 根据ID查询报告评论
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询报告评论", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "report.report.reportcomment.get")
	public ReportCommentVO get(@PathVariable Long id) {

		ReportComment reportComment = reportCommentService.find(id);
		if(reportComment == null){
			throw new ResourceNotFoundException("找不到指定的报告评论，请检查ID");
		}
		ReportCommentVO vo = initViewProperty(reportComment);
		return vo;
	}

	/**
	 * 查询报告评论列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询报告评论列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "report.report.reportcomment.list")
	public PageContent<ReportCommentVO> list(@RequestBody PageSearchRequest<ReportCommentCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<ReportComment> page = reportCommentService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ReportCommentVO> voList = new ArrayList<>();
		for(ReportComment reportComment : page.getContent()){
			voList.add(initViewProperty(reportComment));
		}

		PageContent<ReportCommentVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出报告评论列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出报告评论列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ReportCommentCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ReportCommentCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ReportCommentVO> content = this.list(pageSearchRequest);

        List<ReportCommentVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ReportCommentVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("reportId" ,"报告的主键");
        headMap.put("commentContent" ,"评论内容");

        String title = new String("报告评论");
        String fileName = new String(("报告评论_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ReportCommentVO initViewProperty(ReportComment reportComment){

	    ReportCommentVO vo = new ReportCommentVO();
        BeanUtils.copyProperties(reportComment, vo);


	    //初始化其他对象
        return vo;

	}


}

