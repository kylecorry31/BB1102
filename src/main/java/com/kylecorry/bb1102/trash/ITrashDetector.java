package com.kylecorry.bb1102.trash;

import java.util.List;

public interface ITrashDetector {
    double containsTrash(List<Classification> classifications, TrashRepo trashRepo);
}
