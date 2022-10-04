package com.example.todoseconds.dto;

import lombok.Getter;

@Getter
public class PageResponseDTO {

    private int start;
    private int end;
    private boolean prev,next;
    private int total;
    private PageRequestDTO pageRequestDTO;

    public PageResponseDTO(PageRequestDTO pageRequestDTO, int total){

        this.pageRequestDTO = pageRequestDTO;
        this.total = total;

        //현재 페이지 번호
        int currentPage = pageRequestDTO.getPage();

        int size = pageRequestDTO.getSize();

        //시작 페이지 계산
        this.start = (int)(Math.floor( (currentPage-1)/10.0 ) * 10) + 1;


        //마지막 페이지 계산 123/10.0 = 12.3
        double tempEnd = total/(double)pageRequestDTO.getSize();

        if((this.start + 9) * size < total){
            this.end = this.start + 9;

        }else {
            this.end = (int)(Math.ceil(total/(double)size));
        }

        this.prev = this.start != 1;
        this.next = (this.end * size) < total;

    }
}
