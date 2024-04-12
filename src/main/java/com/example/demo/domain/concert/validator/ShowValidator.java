package com.example.demo.domain.concert.validator;

import com.example.demo.domain.concert.entity.Show;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class ShowValidator {
    public void validShow(List<Show> showList) {
        if(CollectionUtils.isEmpty(showList)){
            throw new RuntimeException("예약 가능한 날짜가 없습니다.");
        }
    }
}
