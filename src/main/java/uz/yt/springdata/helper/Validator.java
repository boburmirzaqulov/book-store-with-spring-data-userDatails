package uz.yt.springdata.helper;

import uz.yt.springdata.dto.BookDTO;
import uz.yt.springdata.dto.PublisherDTO;
import uz.yt.springdata.dto.UserDTO;
import uz.yt.springdata.dto.ValidatorDTO;
import uz.yt.springdata.helper.constants.AppResponseMessages;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    static List<ValidatorDTO> errors = new ArrayList<>();

    public static List<ValidatorDTO> validateBook(BookDTO bookDTO){

       errors.clear();

        if (StringHelper.isValidField(bookDTO.getName())){
            errors.add(new ValidatorDTO("name", AppResponseMessages.EMPTY_FIELD));
        }

        if (StringHelper.isValidField(bookDTO.getGenre())){
            errors.add(new ValidatorDTO("genre", AppResponseMessages.EMPTY_FIELD));
        }

        if (bookDTO.getCost() == null){
            errors.add(new ValidatorDTO("cost", AppResponseMessages.EMPTY_FIELD));
        }else if (bookDTO.getCost().doubleValue() < 0){
            errors.add(new ValidatorDTO("cost", AppResponseMessages.MINUS_VALUE));
        }

        if (bookDTO.getPublishedDate() == null){
            errors.add(new ValidatorDTO("publishedDate", AppResponseMessages.EMPTY_FIELD));
        }else if (!DateHelper.isValidDate(bookDTO.getPublishedDate())){
            errors.add(new ValidatorDTO("publishedDate", AppResponseMessages.DATE_FORMAT_YYYY_MM_DD));
        }

        if (bookDTO.getPageCount() == null){
            errors.add(new ValidatorDTO("pageCount", AppResponseMessages.EMPTY_FIELD));
        }else if (bookDTO.getPageCount() < 1){
            errors.add(new ValidatorDTO("pageCount", AppResponseMessages.MINUS_VALUE));
        }

        if (bookDTO.getAuthor() == null || bookDTO.getAuthor().getId() == null){
            errors.add(new ValidatorDTO("authorId", AppResponseMessages.EMPTY_FIELD));
        }
        if (bookDTO.getPublisherDTO() == null || bookDTO.getPublisherDTO().getId() == null){
            errors.add(new ValidatorDTO(PublisherDTO.class.getSimpleName(), AppResponseMessages.EMPTY_FIELD));
        }

        return errors;
    }

    public static List<ValidatorDTO> validateUser(UserDTO userDTO) {

        errors.clear();

        if (StringHelper.isValidField(userDTO.getFirstName())){
            errors.add(new ValidatorDTO("firstname", AppResponseMessages.EMPTY_FIELD));
        }

        if (StringHelper.isValidField(userDTO.getLastName())){
            errors.add(new ValidatorDTO("lastname", AppResponseMessages.EMPTY_FIELD));
        }

        if (StringHelper.isValidField(userDTO.getPhoneNumber())){
            errors.add(new ValidatorDTO("phoneNumber", AppResponseMessages.EMPTY_FIELD));
        }

        if (userDTO.getAccount().doubleValue() < 0 ){
            errors.add(new ValidatorDTO("account", AppResponseMessages.MINUS_VALUE));
        }

        if (StringHelper.isValidField(userDTO.getPassword())){
            errors.add(new ValidatorDTO("password", AppResponseMessages.EMPTY_FIELD));
        }

        if (StringHelper.isValidField(userDTO.getUsername())){
            errors.add(new ValidatorDTO("username", AppResponseMessages.EMPTY_FIELD));
        }

        return errors;
    }
}
