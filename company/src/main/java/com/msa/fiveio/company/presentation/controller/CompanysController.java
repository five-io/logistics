package com.msa.fiveio.company.presentation.controller;

import com.msa.fiveio.company.application.facade.CompanysFacade;
import com.msa.fiveio.company.presentation.dto.request.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.request.CompanyUpdateRequestDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/companys")
@RequiredArgsConstructor
public class CompanysController {
    private final CompanysFacade companysFacade;

    //업체등록
    // todo. 주문 쪽으로 메세지
    @PostMapping
    public ResponseEntity<CompanyCreateResponseDto> createCompany(@RequestBody CompanyCreateRequestDto requestdto){
        CompanyCreateResponseDto companyCreateResponseDto = companysFacade.createCompany(requestdto);
        return ResponseEntity.ok(companyCreateResponseDto);
    }


    //업체검색

    //업체단건조회
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyGetResponseDto> getCompany(@PathVariable UUID companyId){
        CompanyGetResponseDto companyGetResponseDto = companysFacade.getCompany(companyId);
        return ResponseEntity.ok(companyGetResponseDto);
    }

    //업체수정
    @PatchMapping("/{companyId}")
    public ResponseEntity<CompanyUpdateResponseDto> updateCompany(
            @PathVariable UUID companyId, @RequestBody CompanyUpdateRequestDto requestdto){
        CompanyUpdateResponseDto companyUpdateResponseDto= companysFacade.updateCompany(companyId, requestdto);
        return ResponseEntity.ok(companyUpdateResponseDto);
    }

    //업체삭제
    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID companyId){
        companysFacade.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }
}
