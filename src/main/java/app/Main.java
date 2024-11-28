package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addWeatherView()
                                            .addWeatherHourlyView()
                                            .addWeatherDailyView()
                                            .addChoosePetView()

                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .addWeatherUseCase()
                                            .addWeatherHourlyUseCase()
                                            .addWeatherDailyUseCase()
                                            .addChoosePetUseCase()

                                            .build();

        // set the window size to be 435*410 and prevent it from changing.
        // Yes, these numbers are determined by trail and error.
        final int height = 435;
        final int width = 410;
        application.setSize(width, height);
        application.setResizable(false);
        application.setLocationRelativeTo(null);

//        application.pack();
        application.setVisible(true);
    }
}
