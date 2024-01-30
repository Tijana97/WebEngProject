package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.exceptions.general.BadRequestException;
import ba.edu.ibu.webengineeringproject.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.webengineeringproject.core.model.Hotel;
import ba.edu.ibu.webengineeringproject.core.model.User;
import ba.edu.ibu.webengineeringproject.core.model.enums.UserType;
import ba.edu.ibu.webengineeringproject.core.repository.HotelRepository;
import ba.edu.ibu.webengineeringproject.core.repository.UserRepository;
import ba.edu.ibu.webengineeringproject.rest.dto.HotelDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    public HotelService(HotelRepository hotelRepository, UserRepository userRepository){

        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
    }

    public List<HotelDTO> getHotels(){
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelDTO> hotelDTOS = new ArrayList<>();
        for (Hotel hotel: hotels) {
            String ownerId = hotel.getOwnerId();
            Optional<User> owner = userRepository.findById(ownerId);
            if(owner.isPresent()){
                String ownerName = owner.get().getFirstName() + " " + owner.get().getLastName();
                HotelDTO hotelDTO = new HotelDTO(hotel, ownerName);
                hotelDTOS.add(hotelDTO);
            }
        }
        return hotelDTOS;
    }

    public  List<String> findHotelsBySearch(String search){
        List<Hotel> hotelsByName = hotelRepository.findAllByName(search);
        List<Hotel> hotelsByCity = hotelRepository.findByCity(search);

        for (Hotel hotel : hotelsByName) {
            if(!hotelsByCity.contains(hotel)){
                hotelsByCity.add(hotel);
            }
        }
        List<String> hotelIds = new ArrayList<>();
        for (Hotel hotel: hotelsByCity) {
            hotelIds.add(hotel.getId());
        }

        return hotelIds;
    }

    public HotelDTO getHotelById(String id){
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent()){
            Optional<User> owner = userRepository.findById(hotel.get().getOwnerId());
            Hotel hotelUnwrapped = hotel.get();
            if(owner.isPresent()){
                String ownerName = owner.get().getFirstName() + " " + owner.get().getLastName();
                return new HotelDTO(hotelUnwrapped, ownerName);
            }
        }
        return null;
    }

    public HotelDTO addHotel(Hotel payload){
        String ownerId = payload.getOwnerId();
        Optional<User> owner = userRepository.findById(ownerId);
        if(owner.isPresent() && owner.get().getUserType().equals(UserType.OWNER)){
            Hotel hotel = hotelRepository.save(payload);
            String ownerName = owner.get().getFirstName() + " " + owner.get().getLastName();
            return new HotelDTO(hotel, ownerName);
        } else {
            throw new BadRequestException("Only owner users can add hotels.");
        }
    }

    public HotelDTO updateHotel(Hotel payload, String id){
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("The hotel with the given ID does not exist.");
        }
        payload.setId(id);
        Optional<User> owner = userRepository.findById(payload.getOwnerId());
        if(owner.isPresent() && owner.get().getUserType().equals(UserType.OWNER)){
            Hotel updatedHotel = hotelRepository.save(payload);
            String ownerName = owner.get().getFirstName() + " " + owner.get().getLastName();
            return new HotelDTO(updatedHotel, ownerName);
        } else {
            throw new BadRequestException("Only owner users can add hotels.");
        }
    }

    public void deleteHotel(String id){
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("The hotel with the given ID does not exist.");
        } else {
            hotelRepository.deleteById(id);
        }

    }

}