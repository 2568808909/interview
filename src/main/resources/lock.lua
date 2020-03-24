if redis.call("SETNX",KEYS[1],ARGV[1]) == 1 then
    return redis.call("EXPIRE",KEYS[1],5)
else
    return 0
end