package com.msa.fiveio.user.application;


import com.msa.fiveio.user.model.entity.DeliveryManagers;
import com.msa.fiveio.user.model.entity.DeliveryManagersFactory;
import com.msa.fiveio.user.model.entity.Users;
import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;
import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;
import com.msa.fiveio.user.model.repository.DeliveryManagerRepository;
import com.msa.fiveio.user.model.repository.UsersRepository;
import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.UserErrorCode;
import com.msa.fiveio.user.presentation.dto.DeliveryManagerProfileDto;
import com.msa.fiveio.user.presentation.dto.DeliveryManagerRequestDto;
import com.msa.fiveio.user.presentation.dto.UserResponseDto;
import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryManagerService {

  private final UsersRepository usersRepository;
  private final DeliveryManagerRepository deliveryManagerRepository;

  //  배송 생성
  public DeliveryManagerProfileDto createDeliveryManager(DeliveryManagerRequestDto deliveryManagerRequestDto) {
    Users user = usersRepository.findByUserId(deliveryManagerRequestDto.getUserId())
        .orElseThrow(() -> new CustomException(UserErrorCode.USER_ERROR_CODE));

    // role 확인
    if (user.getRole() != UsersRoleEnum.ROLE_DELIVERY_MANAGER) {
      throw new CustomException(UserErrorCode.ENUM_ERROR_CODE);
    }

    // 이미 등록된 배송담당자인지 확인
    if (deliveryManagerRepository.existsById(deliveryManagerRequestDto.getUserId())) {
      throw new CustomException(UserErrorCode.USER_ALEADY_EXIST);
    }

    //MAX시퀀스 확인
    Integer maxSequence = deliveryManagerRepository.findMaxSequenceByHubId(user.getHubId(),deliveryManagerRequestDto.getManagersType());
   if (maxSequence == 10) {
     throw new CustomException(UserErrorCode.USER_FULL_EXIST);
   }

    // 배송담당자 생성
    DeliveryManagers deliveryManager = DeliveryManagersFactory.createDeliveryManager(
        user,
        maxSequence + 1,
        deliveryManagerRequestDto.getManagersType()
    );
    deliveryManagerRepository.save(deliveryManager);
    return new DeliveryManagerProfileDto(deliveryManager);

  }

    //배송 담당자 조회
    public DeliveryManagerProfileDto getDeliveryManagerProfile(Long userId) {
      DeliveryManagers deliveryManager = deliveryManagerRepository.findByUserId(userId)
          .orElseThrow(() -> new CustomException(UserErrorCode.USER_ERROR_CODE));
      return new DeliveryManagerProfileDto(deliveryManager);
    }


  public UserResponseDto getDeliveryManagerId(UUID hubId, ManagersTypeEnum type) {

    List<UserResponseDto> responseDtos = deliveryManagerRepository.findUserIdByHubId(hubId,type);

      return responseDtos.stream()
          .min(Comparator.comparing(UserResponseDto::getUpdatedAt))
          .orElseThrow(() -> new RuntimeException("No matching manager found"));
  }

}