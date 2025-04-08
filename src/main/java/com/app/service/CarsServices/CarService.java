package com.app.service.CarsServices;

import com.app.entity.cars.*;
import com.app.entity.evalution.Agents;
import com.app.exception.ResourceNotFound;
import com.app.payload.CarsDTO.CarDto;
import com.app.repository.CarsRepo.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final KmsRepository kmsRepository;
    private final ModelRepository modelRepository;
    private final YearRepository yearRepository;
    private final TransmissionRepository transmissionRepository;
    private final LocationRepository locationRepository;


    public CarService(CarRepository carRepository, ModelMapper modelMapper, BrandRepository brandRepository, FuelTypeRepository fuelTypeRepository, KmsRepository kmsRepository, ModelRepository modelRepository, YearRepository yearRepository, TransmissionRepository transmissionRepository, LocationRepository locationRepository) {
        this.carRepository = carRepository;

        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.kmsRepository = kmsRepository;
        this.modelRepository = modelRepository;
        this.yearRepository = yearRepository;
        this.transmissionRepository = transmissionRepository;
        this.locationRepository = locationRepository;
    }

    public CarDto addCarDetails(CarDto carDto) throws Exception {
//        carDto ---> car
//        car --> save
//        saved --> carDto
//        return carDto
        Car car = convertDtoToEntity(carDto);
        if (carDto!= null && carDto.getBrand().getId()!=null){
            Brand br= brandRepository.findById(carDto.getBrand().getId()).orElseThrow(() ->new ResourceNotFound("id is not found"));
                 car.setBrand(br);
        }

        if (carDto!= null && carDto.getModel().getId()!=null){
            Model md = modelRepository.findById(carDto.getModel().getId()).orElseThrow(() -> new  ResourceNotFound("id is not found"));
            car.setModel(md);
        }

        if (carDto!= null && carDto.getFuelType().getId()!=null){
            FuelType ft = fuelTypeRepository.findById(carDto.getFuelType().getId()).orElseThrow(() -> new  ResourceNotFound("id is not found"));
            car.setFuelType(ft);
        }

        if (carDto!= null && carDto.getKms().getId()!=null){
            Kms km = kmsRepository.findById(carDto.getKms().getId()).orElseThrow(() -> new  ResourceNotFound("id is not found"));
            car.setKms(km);
        }

        if (carDto!= null && carDto.getYear().getId()!=null){
            Year yr = yearRepository.findById(carDto.getYear().getId()).orElseThrow(() -> new  ResourceNotFound("id is not found"));
            car.setYear(yr);
        }
        if (carDto!= null && carDto.getTransmission().getId()!=null){
            Transmission tm = transmissionRepository.findById(carDto.getTransmission().getId()).orElseThrow(() -> new  ResourceNotFound("id is not found"));
            car.setTransmission(tm);
        }
        if (carDto!= null && carDto.getLocation().getId()!=null){
            Location lt= locationRepository.findById(carDto.getLocation().getId()).orElseThrow(() -> new  ResourceNotFound("id is not found"));
            car.setLocation(lt);
        }

        Car saved = carRepository.save(car);


        return convertEntityToDto(saved);

    }

   Car convertDtoToEntity(CarDto carDto){

        return modelMapper.map(carDto,Car.class);
   }

    CarDto convertEntityToDto(Car car){

               return modelMapper.map(car, CarDto.class);
    }

    public List<CarDto> getAllcars(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<Car> carList = carRepository.findAll(page);
        List<Car> content = carList.getContent();
       return content.stream().map(car->modelMapper.map(car,CarDto.class)).collect(Collectors.toList());
    }

    public void deleteByCar(Long id) {
        if(carRepository.existsById(id)){
            carRepository.deleteById(id);
        }
    }

    public void updateCar(Long id, CarDto carDto) {
        Optional<Car> opCar = carRepository.findById(id);
        Car car = opCar.get();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setFuelType(carDto.getFuelType());
        car.setKms(carDto.getKms());
        car.setYear(carDto.getYear());
        car.setLocation(carDto.getLocation());
        car.setTransmission(carDto.getTransmission());
         carRepository.save(car);

    }
    public CarDto findById(Long id){
       Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Record not found with id " + id));
       CarDto carDto =new CarDto();
       carDto.setBrand(car.getBrand());
       carDto.setKms(car.getKms());
       carDto.setModel(car.getModel());
       carDto.setFuelType(car.getFuelType());
       carDto.setYear(car.getYear());
       carDto.setTransmission(car.getTransmission());
       carDto.setLocation(car.getLocation());
       return carDto;
    }
}
