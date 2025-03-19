package com.msa.fiveio.company.application.usecase;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.CompanysErrorCode;
import com.msa.fiveio.company.model.entity.Companys;
import com.msa.fiveio.company.model.repository.CompanysRepository;
import com.msa.fiveio.company.presentation.dto.request.CompanyCreateRequestDto;
import com.msa.fiveio.company.presentation.dto.request.CompanyUpdateRequestDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyCreateResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyGetResponseDto;
import com.msa.fiveio.company.presentation.dto.response.CompanyUpdateResponseDto;
import com.msa.fiveio.company.presentation.mapper.CompanysMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //삭제
        companysRepository.deleteById(companyId);
    }

    @Override
    public CompanyUpdateResponseDto updateCompany(UUID companyId,
            CompanyUpdateRequestDto requestDto) {
        //id로 데이터에 저장되어 있는지 확인
        Companys company = companysRepository.findById(companyId).orElseThrow(
                () -> new CustomException(CompanysErrorCode.COMPANYS_NOT_FOUND));
        //DTO 받아온걸로 업데이트
        company.update(requestDto);
        //업데이트한 엔티티로 save
        companysRepository.save(company);
        return CompanysMapper.entityToUpdateCompanyResponseDto(company);

    }
}
