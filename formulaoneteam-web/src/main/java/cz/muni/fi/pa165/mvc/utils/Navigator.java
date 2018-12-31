package cz.muni.fi.pa165.mvc.utils;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class Navigator {

    public static String openForbiddenPage(String message) {
        return "redirect:/forbidden?message=" + message;
    }

    public static String openNotFoundPage(String message) {
        return "redirect:/notFound?message=" + message;
    }

    private Navigator() {
        throw new IllegalAccessError("This constructor should never get called.");
    }
}
