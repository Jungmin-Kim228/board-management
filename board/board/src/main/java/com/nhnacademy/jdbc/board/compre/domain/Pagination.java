package com.nhnacademy.jdbc.board.compre.domain;

import lombok.Data;

@Data
public class Pagination {
    final int rowCount = 20; //한 페이지당 게시글 개수
    final int pageCount = 3; // 한 블럭당 페이지 개수

    int totalCount; // 총 게시글 개수
    int currentPage; // 현재 페이지

    int startPage = 1; // 한 블럭 시작페이지
    int endPage;

    int totalPageCount; // 총 페이지 개수
    boolean isPrev = false;
    boolean isNext = false;

    int offset;

    public Pagination(final int totalCount, final int currentPage) {

        setTotalPageCount(totalCount, this.rowCount);

        setStartPage(this.startPage, currentPage, this.pageCount);

        setEndpage(this.startPage, this.pageCount, this.totalPageCount);

        isPrev(currentPage, this.pageCount);

        isNext(this.endPage, this.totalPageCount);

        setOffset(currentPage, this.rowCount);
    }

    private void setTotalPageCount(final int totalCount, final int rowCount) {
        this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
    }

    private void setStartPage(final int startPage, final int currentPage, final int pageCount) {
        this.startPage = startPage + (((currentPage - startPage) / pageCount) * pageCount);
    }

    private void setEndpage(final int startPage, final int pageCount, final int totalPageCount) {
        this.endPage = ((startPage - 1) + pageCount) < totalPageCount ? (startPage - 1) + pageCount : totalPageCount;
    }

    private void isPrev(final int currentPage, final int pageCount) {
        this.isPrev = 1 < ((currentPage * 1.0) / pageCount);
    }

    private void isNext(final int endPage, final int totalPageCount) {
        this.isNext = endPage < totalPageCount;
    }

    private void setOffset(final int currentPage, final int rowCount) {
        this.offset = (currentPage - 1) * rowCount;
    }
}
