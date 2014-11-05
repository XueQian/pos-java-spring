package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.entity.Item;

public interface ItemService {

    Item getItem(String barcode);
}
