package com.doeiqts.pizzavoter.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;

public class Vote implements Comparable<Vote> {
    private Set<String> usersWhoVoted = new HashSet<>();

    public Vote() {
        // Needed for Objectify
    }

    public Vote(String userName) {
        this.usersWhoVoted.add(userName);
    }

    public Vote addToVote(String userName) {
        this.usersWhoVoted.add(userName);
        return this;
    }

    public Vote removeVote(String userName) {
        this.usersWhoVoted.remove(userName);
        return this;
    }

    public Integer getCount() {
        return usersWhoVoted.size();
    }

    public Set<String> getUsersWhoVoted() {
        return usersWhoVoted;
    }

    @Override
    public int compareTo( final Vote o) {
        return Integer.compare(this.getCount(), o.getCount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        return usersWhoVoted != null ? usersWhoVoted.equals(vote.usersWhoVoted) : vote.usersWhoVoted == null;

    }

    @Override
    public int hashCode() {
        return usersWhoVoted != null ? usersWhoVoted.hashCode() : 0;
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
