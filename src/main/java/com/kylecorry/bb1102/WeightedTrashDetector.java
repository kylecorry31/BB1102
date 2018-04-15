package com.kylecorry.bb1102;

import java.util.List;

public class WeightedTrashDetector implements ITrashDetector {
    @Override
    public double containsTrash(List<Classification> classifications, TrashRepo trashRepo) {
        double trashProbability = 0;
        int trashCount = 0;
        for (Classification classification: classifications){
            if(trashRepo.isTrash(classification.getName())){
                trashCount++;
                trashProbability += classification.getProbability();
            }
        }
        if(trashCount == 0){
            return 0;
        }
        return trashProbability / trashCount;
    }
}
