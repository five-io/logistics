package com.msa.fiveio.company.presentation.controller;

import com.msa.fiveio.company.application.facade.CompanysFacade;
import com.msa.fiveio.company.presentation.dto.request.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.request.CompanyUpdateRequestDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyUpdateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companys")
@RequiredArgsConstructor
@Tag(name = "Company Service", description = "업체 서비스 API")
public class CompanysController {

    private final CompanysFacade companysFacade;

    //업체등록
    // todo. hub와의 통신(faignclient)
    @Operation(summary = "Company 등록", description = "Company 등록 api 입니다.")
    @PostMapping
    public ResponseEntity<CompanyCreateResponseDto> createCompany(
            @RequestBody CompanyCreateRequestDto requestdto) {
        CompanyCreateResponseDto companyCreateResponseDto = companysFacade.createCompany(
                requestdto);
        return ResponseEntity.ok(companyCreateResponseDto);
    }

    //업체단건조회
    @Operation(summary = "Company 단건조회", description = "Company 단건조회 api 입니다.")
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyGetResponseDto> getCompany(@PathVariable UUID companyId) {
        CompanyGetResponseDto companyGetResponseDto = companysFacade.getCompany(companyId);
        return ResponseEntity.ok(companyGetResponseDto);
    }

    //업체수정
    @Operation(summary = "Company 수정", description = "Company 수정 api 입니다.")
    @PatchMapping("/{companyId}")
    public ResponseEntity<CompanyUpdateResponseDto> updateCompany(
            @PathVariable UUID companyId, @RequestBody CompanyUpdateRequestDto requestdto) {
        CompanyUpdateResponseDto companyUpdateResponseDto = companysFacade.updateCompany(companyId,
                requestdto);
        return ResponseEntity.ok(companyUpdateResponseDto);
    }

    //업체삭제
    @Operation(summary = "Company 삭제", description = "Company 삭제 api 입니다.")
    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID companyId) {
        companysFacade.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }

    //업체검색


}
