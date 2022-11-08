package goanvi.prog.lab3.utils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
@ManagedBean
@SessionScoped
@FacesValidator("yValidator")
public class YValidator implements Validator<Double> {
    private final double yMin = -3;
    private final double yMax = 3;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double o) throws ValidatorException {
        Double model = o;
        if (model<yMin || model>yMax){
            FacesMessage message = new FacesMessage("Y: value must be between -3 and 3");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            System.out.println("Exception");
            throw new ValidatorException(message);
        }
    }
}
