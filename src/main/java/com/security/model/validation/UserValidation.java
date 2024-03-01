package com.security.model.validation;

import com.security.model.dto.ResponseDto;
import com.security.model.entity.User;

public class UserValidation {

    public ResponseDto validate(User user){
        ResponseDto response = new ResponseDto();

        response.setNumOfError(0);

        if (user.getName() == null ||
            user.getName().length() < 3 ||
            user.getName().length() > 15
        ){
            response.setNumOfError(response.getNumOfError() + 1);
            response.setMessage("Nombre no puede ser nulo; no menor que 3; no mayor que 15");
        }

        if (user.getEmail() == null ||
            !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        ){
            response.setNumOfError(response.getNumOfError() + 1);
            response.setMessage("Email no valido");
        }

        if (user.getPassword() == null ||
            !user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=-*[a-zA-Z])-{8,16}$")
        ){
            response.setNumOfError(response.getNumOfError() + 1);
            response.setMessage("La constraseña debe tener entre 8 y 16 caracteres, al menos 1 numero, 1 minuscula y mayuscula");
        }


        return response;
    }
}
