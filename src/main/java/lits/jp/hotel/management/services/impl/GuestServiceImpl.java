package lits.jp.hotel.management.services.impl;

import lits.jp.hotel.management.dtos.GuestsDTO;
import lits.jp.hotel.management.exceptions.GuestNotFoundException;
import lits.jp.hotel.management.models.Guests;
import lits.jp.hotel.management.repository.GuestsRepository;
import lits.jp.hotel.management.services.GuestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    GuestsRepository guestsRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public GuestsDTO findGuest(GuestsDTO guestToFind) {
        throw new NotImplementedException(); // IDK how to do findBy(example) with crudRepo, tbh
    }

    @Override
    public GuestsDTO findGuestById(Integer id) {
        return modelMapper.map(guestsRepository.findById(id).orElseThrow(
                () ->  new GuestNotFoundException("Guest with id " + id + " not found")
        ), GuestsDTO.class);
    }

    @Override
    public GuestsDTO addGuest(GuestsDTO guestToAdd) {
        return modelMapper.map(guestsRepository.save(
                modelMapper.map(guestToAdd, Guests.class)), GuestsDTO.class);
    }

    @Override
    public Boolean removeGuestById(Integer id) {
        guestsRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean removeGuest(GuestsDTO guestToRemove) {
        guestsRepository.delete(modelMapper.map(guestToRemove, Guests.class));
        return true;
    }

    @Override
    public GuestsDTO updateGuest(GuestsDTO initGuest, GuestsDTO updatedGuest) {
        throw new NotImplementedException();
    }

    @Override
    public GuestsDTO updateGuestById(Integer id, GuestsDTO updatedGuest) {
        GuestsDTO toUpdate = modelMapper.map(guestsRepository.findById(id).orElseThrow(
                () ->  new GuestNotFoundException("Guest with id " + id + " not found")
        ),GuestsDTO.class);

        toUpdate.setFirstName(updatedGuest.getFirstName() != null ?
                updatedGuest.getFirstName() : toUpdate.getFirstName());

        toUpdate.setLastName(updatedGuest.getLastName() != null ?
                updatedGuest.getLastName() : toUpdate.getLastName());

        toUpdate.setEmail(updatedGuest.getEmail() != null ?
                updatedGuest.getEmail() : toUpdate.getEmail());

        toUpdate.setTelephone(updatedGuest.getTelephone() != null ?
                updatedGuest.getTelephone() : toUpdate.getTelephone());

        toUpdate.setAge(updatedGuest.getAge() != null ?
                updatedGuest.getAge() : toUpdate.getAge());

        Guests update = modelMapper.map(toUpdate,Guests.class);

        update.setGuestId(id);

        return modelMapper.map(guestsRepository.save(update),GuestsDTO.class);
    }
}
