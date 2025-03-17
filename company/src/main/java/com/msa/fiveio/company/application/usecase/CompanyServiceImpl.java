package com.msa.fiveio.company.application.usecase;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.CompanysErrorCode;
import com.msa.fiveio.company.model.entity.Companys;
import com.msa.fiveio.company.model.repository.CompanysRepository;
import com.msa.fiveio.company.presentation.dto.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.CompanyGetResponseDto;
import com.msa.fiveio.company.presentation.mapper.CompanysMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanysRepository companysRepository;

    //업체생성
    @Override
    public CompanyCreateResponseDto createCompanys(CompanyCreateRequestDto requestDto) {
        //requestdto -> 엔티티 변환
        Companys company = CompanysMapper.CreateRequestDtoToEntity(requestDto);
        //엔티티 저장
        companysRepository.save(company);
        //dto 반환
        return CompanysMapper.entityToCreateCompanysResponseDto(company);
    }

    public CompanyGetResponseDto getCompanys(UUID companyId) {
        //id로 리포지토리에서 찾아서 엔티티 만들기
        Companys company = companysRepository.findById(companyId).orElseThrow(
                () -> new CustomException(CompanysErrorCode.COMPANYS_NOT_FOUND));

        return CompanysMapper.entityToGetCompanyResponseDto(company);
    }

    @Override
    public void deleteCompany(UUID companyId) {
        //id로 리포지토리에서 찾기
        Companys company = companysRepository.findById(companyId).orElseThrow(
                () -> new CustomException(CompanysErrorCode.COMPANYS_NOT_FOUND));
        //삭제처리
        //todo. softdelete 구현
    }
}
