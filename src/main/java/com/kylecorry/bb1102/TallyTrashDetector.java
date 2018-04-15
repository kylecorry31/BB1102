package com.kylecorry.bb1102;

import java.util.List;

public class TallyTrashDetector implements ITrashDetector {
    @Override
    public double containsTrash(List<Classification> classifications, TrashRepo trashRepo) {
        int trashCount = 0;
        for (Classification classification: classifications){
            if(trashRepo.isTrash(classification.getName())){
                trashCount++;
            }
        }
        return trashCount / (double) classifications.size();
    }
}
