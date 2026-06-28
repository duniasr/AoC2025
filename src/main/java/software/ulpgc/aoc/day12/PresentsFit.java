package software.ulpgc.aoc.day12;

public interface PresentsFit {
    
    boolean fitUnderRegion(TreeRegion region);
    
    static PresentsFit fitLogic() {
        return new PresentsFitLogic();
    }
}
