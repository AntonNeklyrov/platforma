package com.neklyudov.platforma.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SubscriptionPage {
    public static final int PAGE_SIZE = 10;

    /*тут хранятся не все записи, а только одна страница*/
    private List<Subscription> subscriptions;
    /*кол-во записей во всей таблице (нужно для пагинации)*/
    private int rowsCount;
}
