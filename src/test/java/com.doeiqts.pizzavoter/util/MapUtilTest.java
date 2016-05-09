package com.doeiqts.pizzavoter.util;



import com.doeiqts.pizzavoter.domain.Vote;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtilTest
{
    @Test
    public void testSortByValue()
    {
        Map<String, Vote> testMap = new LinkedHashMap<>();
        String alpha = "alpha";
        String beta = "beta";
        String gamma = "gamma";
        String psi = "psi";
        String omega = "omega";

        Vote vote1 = new Vote("username");
        vote1.addToVote("a").addToVote("b").addToVote("c");
        Vote vote2 = new Vote("username2");
        vote2.addToVote("a").addToVote("d");
        Vote vote3 = new Vote("magic");
        vote3.addToVote("1").addToVote("2").addToVote("3").addToVote("4").addToVote("5").addToVote("6").addToVote("7").addToVote("8");
        Vote vote4 = new Vote("one");
        testMap.put(alpha,vote1);
        testMap.put(gamma,vote2);
        testMap.put(psi,vote3);
        testMap.put(beta,vote4);
        testMap.put(omega,vote1);

        testMap = MapUtil.sortByValue( testMap, false );

        Vote previous = null;
        for(Map.Entry<String, Vote> entry : testMap.entrySet()) {
            Assert.assertNotNull( entry.getValue() );
            if (previous != null) {
                Assert.assertTrue( entry.getValue().getCount() >= previous.getCount() );
            }
            previous = entry.getValue();
        }

        testMap = MapUtil.sortByValue( testMap, true);

        previous = null;
        for(Map.Entry<String, Vote> entry : testMap.entrySet()) {
            Assert.assertNotNull( entry.getValue() );
            if (previous != null) {
                Assert.assertTrue( entry.getValue().getCount() <= previous.getCount() );
            }
            previous = entry.getValue();
        }
    }
}
