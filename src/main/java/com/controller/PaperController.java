package com.controller;

import com.pojo.Paper;
import com.service.PaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/paper")
@Api(value = "论文接口",tags = "论文增删改查接口")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping("/allPaper")
    public String list(Model model){
        List<Paper> list = paperService.queryAllPaper();
        model.addAttribute("list",list);
        return "allPaper";
    }

    @ApiOperation(value="添加用户列表页面", notes="添加用户列表页面")
    @RequestMapping("toAddPaper")
    public String toAddPaper(){
        return "addPaper";
    }

    @ApiOperation(value="创建用户" ,notes="创建用户时的注意事项")
    @ApiImplicitParam(name = "paper", value = "详细实体paper", required = true, dataType = "Paper")
    @RequestMapping("/addPaper")
    public String addPaper(Paper paper){
        paperService.addPaper(paper);
        return "redirect:/paper/allPaper";
    }

    @ApiOperation(value="删除用户" ,notes="根据Id删除用户")
    @ApiImplicitParam(name = "paperId", value = "详细实体paperId", required = true, dataType = "Int")
    @RequestMapping("/del/{paperId}")
    public String deletePaper(@PathVariable("paperId") Long id){
        paperService.deletePaperById(id);
        return "redirect:/paper/allPaper";
    }

    @RequestMapping("toUpdatePaper")
    public String toUpdatePaper(Model model,Long id){
        model.addAttribute("paper",paperService.queryById(id));
        return "updatePaper";
    }

    @ApiOperation(value="修改用户" ,notes="修改用户细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paperId", value = "用户id", required = true, paramType = "path", dataType = "Int"),
            @ApiImplicitParam(name = "user", value = "用户详细实体Paper", required = true, paramType = "body", dataType = "Paper")
    })
    @RequestMapping("/updatePaper")
    public String updatePaper(Model model,Paper paper){
        paperService.updatePaper(paper);
        paper = paperService.queryById(paper.getPaperId());
        model.addAttribute("paper",paper);
        return "redirect:/paper/allPaper";
    }

}
