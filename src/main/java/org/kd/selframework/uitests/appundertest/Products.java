package org.kd.selframework.uitests.appundertest;

public enum Products {
    IPhone5, MagicMouse, iPodNanoBlue, AppleTV, AppleIpad2, SennheiserRs120, AppleiPad6, AppleIphone4s;

    public String getFullName() {
        final String fullNames[] = {"iPhone 5", "Magic Mouse", "iPod Nano Blue", "Apple TV"
                , "Apple iPad 2 16GB, Wi-Fi, 9.7in – Black", "Sennheiser RS 120", "Apple iPad 6 32GB (White, 3D)"
                , "Apple iPhone 4S 16GB SIM-Free – Black"};

        return fullNames[ordinal()];
    }


}
