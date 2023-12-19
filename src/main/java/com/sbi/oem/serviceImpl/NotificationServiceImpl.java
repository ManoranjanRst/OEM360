package com.sbi.oem.serviceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sbi.oem.dto.Response;
import com.sbi.oem.model.DepartmentApprover;
import com.sbi.oem.model.Notification;
import com.sbi.oem.model.Recommendation;
import com.sbi.oem.model.User;
import com.sbi.oem.repository.DepartmentApproverRepository;
import com.sbi.oem.repository.NotificationRepository;
import com.sbi.oem.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private DepartmentApproverRepository departmentApproverRepository;

	@Override
	public void save(Recommendation recommendation) {
		try {
			Optional<DepartmentApprover> departmentApprover = departmentApproverRepository
					.findAllByDepartmentId(recommendation.getDepartment().getId());
			List<User> userList = Arrays.asList(departmentApprover.get().getAgm(), departmentApprover.get().getApplicationOwner());
			
			for (User user : userList) {
				Notification newNotification = new Notification();
				newNotification.setMessage(recommendation.getDescriptions());
				newNotification.setReferenceId(recommendation.getReferenceId());
				newNotification
						.setMessage("New Recommendation Alert. Reference ID - " + recommendation.getReferenceId());
				newNotification.setCreatedAt(new Date());
				newNotification.setUpdatedAt(new Date());
				newNotification.setIsSeen(false);
				newNotification.setUser(user);
				notificationRepository.save(newNotification);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Response<?> getNotificationByUserId(Long userId) {
		try {
			List<Notification> list = notificationRepository.findByUserId(userId);
			return new Response<>(HttpStatus.OK.value(), "success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public void markAsSeen(Long userId) {
		try {
			List<Notification> list = notificationRepository.findByUserId(userId);
			for(Notification notification : list) {				
				notificationRepository.markAsSeen(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
