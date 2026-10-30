// Author: haveela | Project: Route_Planning_Automation
package src.main.Utils;

import java.util.HashMap;
import java.util.Map;

public class RequestHeaderMap {
    //Storing the Request Headers in a map
    public Map<String, Object> requestHeaderMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("customerid", "null");
        map.put("scope", "[{\"scopeId\":null,\"scopeName\":\"Resource\",\"scopeType\":\"NON_HIERARCHY\",\"scopeValue\":[\"*\"],\"order\":null,\"parents\":null,\"hasAccess\":false}]");
        map.put("csrf-token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluIiwiaXNzIjoidmFzc2FyIiwiZXhwIjoxNjg0Mzk0OTA0LCJ0bXAiOjE2ODM1MzA5MDQ3ODl9.LVPRJv3yelIPdXRu5aOUXD63lRbM8NN2AU1DF6ysjXo");
        map.put("authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ5dzg0eTR2MlQ5ZVk0MDBaYjJLVDV2M05UOFdHMnlnTHVudll2SVZ2MnBzIn0.eyJleHAiOjE2ODM2OTY2MDMsImlhdCI6MTY4MzUyMzgwNCwi" +
                "YXV0aF90aW1lIjoxNjgzNTIzODAzLCJqdGkiOiIxNWUwNTMxYS00NzQ1LTRiOTItYjc2Mi0wODdlZmY4NDNlNjMiLCJpc3MiOiJodHRwczovL2Rldi5pcWNvbm5lY3QuaXEzNjAuY2xvdWQvYXV0aC9yZWFsbXMvc3RhbmxleSIsImF1ZCI6WyJyZWFsbS1t" +
                "YW5hZ2VtZW50IiwiYWNjb3VudCJdLCJzdWIiOiJkZDQ2MTc1Ni1kMGI1LTQwYjctYjIyZi1kYzcyMDUxOWNkYWMiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJoYXR0cmljayIsIm5vbmNlIjoiZTA0Y2I4ZjYtMjIzMS00MTY3LTk5Y2EtOWQyMzg0NWQ0YjFkI" +
                "iwic2Vzc2lvbl9zdGF0ZSI6IjNlYzc0OTg3LTFjYWYtNGU2ZC1iMWQ2LWFiZjc5YTQxMjY1NSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaX" +
                "phdGlvbiIsImRlZmF1bHQtcm9sZXMtc3RhbmxleSJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InJlYWxtLW1hbmFnZW1lbnQiOnsicm9sZXMiOlsidmlldy1yZWFsbSIsInZpZXctaWRlbnRpdHktcHJvdmlkZXJzIiwibWFuYWdlLWlkZW50aXR5LXByb3ZpZGVy" +
                "cyIsImltcGVyc29uYXRpb24iLCJyZWFsbS1hZG1pbiIsImNyZWF0ZS1jbGllbnQiLCJtYW5hZ2UtdXNlcnMiLCJxdWVyeS1yZWFsbXMiLCJ2aWV3LWF1dGhvcml6YXRpb24iLCJxdWVyeS1jbGllbnRzIiwicXVlcnktdXNlcnMiLCJtYW5hZ2UtZXZlbnRzIi" +
                "wibWFuYWdlLXJlYWxtIiwidmlldy1ldmVudHMiLCJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwibWFuYWdlLWF1dGhvcml6YXRpb24iLCJtYW5hZ2UtY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3" +
                "VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSIsInNpZCI6IjNlYzc0OTg3LTFjYWYtNGU2ZC1iMWQ2LWFiZjc5YTQxMjY1NSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwi" +
                "bmFtZSI6IlN1cGVyIEFkbWluIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic3VwZXJhZG1pbiIsImdpdmVuX25hbWUiOiJTdXBlciIsImZhbWlseV9uYW1lIjoiQWRtaW4iLCJlbWFpbCI6InN1cGVyYWRtaW5AZ21haWwuY29tIn0.hXLpGliYufp2F5aiR1waHp" +
                "w1ffBDg7pryqDrEaggnKlEdJ9N18k_ciaHwIjTNurjU9SUZomrDPsZe5klbIUasAF65dU8aUJb5NV4hbrK7aTZvwMMd0_wyKzNRbG_wlciG5E3-Rz5D10f9rDwE8e7FigPpTemVFUd3UXLYpPQmwu0sUfSaZVpbyExK9PKu5uv0K29mICu0l7QBHYoyC1S-GJ" +
                "Elzl_81U-JDQFdmtJznIdaG8w-BGz128TEl0cRYpu8K8zjD3j36b_lExlRxKgBQLieYYl4ng3AG2a8kq-olgc3B8EPkxsKt3epbboNLn9IN0Q92vBMBpIAin_PZtCxw");
        map.put("userid", "dd461756-d0b5-40b7-b22f-dc720519cdac");
        return map;
    }
}
