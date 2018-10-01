package com.doordash.doordashlite.ui.restaurantDetails;

import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.data.Restaurant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tarun on 10/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class RestaurantDetailsPresenterTest {
    @Mock
    private RestaurantDetailContract.View mockView;

    @Mock
    private Repository mockDataRepository;

    @Mock
    private Restaurant restaurant;


    private RestaurantDetailContract.Presenter presenter;
    @Before
    public void setup() {
        presenter = new RestaurantDetailsPresenter(mockView, mockDataRepository);
    }
    @Test
    public void onCreate_test() {
        when(restaurant.getName()).thenReturn("testName");
        when(restaurant.getDeliveryFee()).thenReturn("100.00");
        when(restaurant.getPhone()).thenReturn("5053406053");
        presenter.onCreate(restaurant);
        verify(mockView).setResName(restaurant.getName());
        verify(mockView).setResDeliveryFee(restaurant.getDeliveryFee());
        verify(mockView).setResPhone(restaurant.getPhone());
    }
    @Test
    public void onCreate_test_Restaurant_ISNULL() {
        restaurant = null;
        presenter.onCreate(restaurant);
        verify(mockView).showErrorMessage();
    }
}
