package lits.jp.hotel.management.service;

import lits.jp.hotel.management.repository.GuestsRepository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceImplTest {

    @Mock
    GuestsRepository guestsRepositoryMock;

    @Mock
    GuestService guestServiceMock;


}
