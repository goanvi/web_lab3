package goanvi.prog.lab3.model;

import goanvi.prog.lab3.DTO.MarkDTO;
import goanvi.prog.lab3.Service.MarkService;
import org.icefaces.ace.model.table.LazyDataModel;
import org.icefaces.ace.model.table.SortCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LazyMarkDataModel extends LazyDataModel<MarkDTO> {
    private final MarkService markService;

    public LazyMarkDataModel(MarkService markService) {
        this.markService = markService;
    }

    @Override
    public List<MarkDTO> load(int first, int pageSize, SortCriteria[] arg2, Map<String, String> arg3) {
        List<MarkDTO> marksDTO;
        if(!arg3.isEmpty()){
            if (arg3.containsKey("leadTime") && arg3.containsKey("time")){
                marksDTO = markService.findByLeadTimeAndTime(arg3.get("time"), Long.parseLong(arg3.get("leadTime")));
            } else if (arg3.containsKey("time")){
                marksDTO = markService.findByLeadTimeAndTime(arg3.get("time"), null);
            } else if (arg3.containsKey("leadTime")) {
                marksDTO = markService.findByLeadTimeAndTime(null, Long.parseLong(arg3.get("leadTime")));
            }else marksDTO = new ArrayList<>();
            setRowCount(marksDTO.size());
            return marksDTO;
        }else {
            marksDTO = markService.getRange(first, pageSize);
        }
        setRowCount(markService.getNumberOfElements().intValue());
        return marksDTO;
    }


}
