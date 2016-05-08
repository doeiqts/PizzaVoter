package com.doeiqts.pizzavoter.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;

public class Vote implements Comparable<Vote> {
    private Integer count;
    private Set<String> usersWhoVoted = new HashSet<>();

    public Vote(String userName) {
        this.count = 1;
        this.usersWhoVoted.add(userName);
    }

    public Vote addToVote(String userName) {
        if(!this.usersWhoVoted.contains(userName)) {
            this.count = (null == count) ? 1 : count + 1;
            this.usersWhoVoted.add(userName);
        }
        return this;
    }

    public Vote removeVote(String userName) {
        this.count = count - 1;
        this.usersWhoVoted.remove(userName);
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Set<String> getUsersWhoVoted() {
        return usersWhoVoted;
    }

    @Override
    public int compareTo( final Vote o) {
        return Integer.compare(this.count, o.getCount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (count != null ? !count.equals(vote.count) : vote.count != null) return false;
        return usersWhoVoted != null ? usersWhoVoted.equals(vote.usersWhoVoted) : vote.usersWhoVoted == null;

    }

    @Override
    public int hashCode() {
        int result = count != null ? count.hashCode() : 0;
        result = 31 * result + (usersWhoVoted != null ? usersWhoVoted.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
