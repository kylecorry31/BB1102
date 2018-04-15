package com.kylecorry.bb1102.trash;

import java.util.List;

public class HighestProbabilityTrashDetector implements ITrashDetector {
    @Override
    public double containsTrash(List<Classification> classifications, TrashRepo trashRepo) {
        for (Classification classification: classifications){
            if(trashRepo.isTrash(classification.getName())){
                return classification.getProbability();
            }
        }
        return 0;
    }
}
