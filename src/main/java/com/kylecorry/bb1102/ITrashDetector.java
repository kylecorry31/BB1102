package com.kylecorry.bb1102;

import java.util.List;

public interface ITrashDetector {
    double containsTrash(List<Classification> classifications, TrashRepo trashRepo);
}
