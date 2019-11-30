package com.dao;

import com.pojo.Paper;

import java.util.List;

public interface PaperDao {
    /**
     * 添加论文
     * @param paper
     * @return
     */
    int addPaper(Paper paper);

    /**
     * 删除论文
     * @param id
     * @return
     */
    int deletePaperById(long id);

    /**
     * 更新修改论文
     * @param paper
     * @return
     */
    int updatePaper(Paper paper);

    /**
     * 根据id查找论文
     * @param id
     * @return
     */
    Paper queryById(long id);

    /**
     * 查找所有论文
     * @return
     */
    List<Paper> queryAllPaper();
}
