package com.parcel.repository;

import com.parcel.entity.ItemManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemManagementRepo extends JpaRepository<ItemManagement,String> {

}
