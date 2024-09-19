package br.com.fiap.ez.fastfood.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.infrastructure.persistence.OrderEntity;
import br.com.fiap.ez.fastfood.infrastructure.persistence.PaymentEntity;

public class PaymentMapper {

	public static Payment entityToDomain(PaymentEntity entity) {
		if (entity == null) {
			return null;
		}
		return new Payment(entity.getId(), OrderMapper.entityToDomain(entity.getOrder()),
				CustomerMapper.entityToDomain(entity.getCustomer()), entity.getPaymentDate(), entity.getPaymentPrice(),
				entity.getPaymentStatus());
	}

	// Convert Payment (Domain) to PaymentEntity (Persistence)
	public static PaymentEntity domainToEntity(Payment payment) {
		if (payment == null) {
			return null;
		}
		PaymentEntity entity = new PaymentEntity();
		entity.setId(payment.getId());
		
		if (payment.getOrder() != null && payment.getOrder().getId() != null) {
	        OrderEntity orderEntity = new OrderEntity();
	        orderEntity.setId(payment.getOrder().getId());
	        entity.setOrder(orderEntity);  
	    }
		entity.setCustomer(CustomerMapper.domainToEntity(payment.getCustomer())); 
																					
		entity.setPaymentDate(payment.getPaymentDate());
		entity.setPaymentPrice(payment.getPaymentPrice());
		entity.setPaymentStatus(payment.getPaymentStatus());
		return entity;
	}

	// Convert a List of PaymentEntity (Persistence) to a List of Payment (Domain)
	public static List<Payment> entityToDomain(List<PaymentEntity> entities) {
		return entities.stream().map(PaymentMapper::entityToDomain).collect(Collectors.toList());
	}

	// Convert a List of Payment (Domain) to a List of PaymentEntity (Persistence)
	public static List<PaymentEntity> domainToEntity(List<Payment> payments) {
		return payments.stream().map(PaymentMapper::domainToEntity).collect(Collectors.toList());
	}
	
	  public static PaymentDTO domainToResponseDto(Payment payment) {
	        if (payment == null) {
	            return null;
	        }
	        PaymentDTO dto = new PaymentDTO();
	        dto.setPaymentId(payment.getOrder() != null ? payment.getOrder().getId() : null);
	        dto.setPaymentStatus(payment.getPaymentStatus().name());
	        return dto;
	    }

}
