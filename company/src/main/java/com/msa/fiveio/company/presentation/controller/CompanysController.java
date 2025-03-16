package com.msa.fiveio.company.presentation.controller;

import com.msa.fiveio.company.application.facade.CompanysFacade;
import com.msa.fiveio.company.presentation.dto.CompanyRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companys")
@RequiredArgsConstructor
public class CompanysController {
    private final CompanysFacade companysFacade;

    //업체등록
    // todo. 주문 쪽으로 메세지
    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(@RequestBody CompanyRequestDto requestdto){
        CompanyResponseDto companyResponseDto = companysFacade.createCompany(requestdto);
        return ResponseEntity.ok(companyResponseDto);
    }



    //업체검색

    //업체단건조회

    //업체수정

    //업체삭제



}
