package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Construction implements Serializable {

    private static final String DATA_DIR_PATH = "./data/";
    private static final String DATA_FILE_NAME = "Constructions.dt";
    private static List<Construction> extent = new ArrayList<>();

    private String name;
    private ConstructionType constructionType;
    private LocalDate constructionDate;
    private int localsNo;
    private LocalDate destructionDate;
    private final List<String> protection = new ArrayList<>();
    private Location location;
    private final Set<String> discoveredResources = new HashSet<>();
    private ConstructionStatus status;
    private float wealth;

    private static final Set<String> discoveredProtectionStructures = new HashSet<>();

    static {
        discoveredProtectionStructures.add("Walls");
    }

    public Construction(String name, ConstructionType constructionType, LocalDate constructionDate, int localsNo, Location location, ConstructionStatus status, float wealth) {
        setName(name);
        setConstructionType(constructionType);
        setConstructionDate(constructionDate);
        setLocalsNo(localsNo);
        setLocation(location);
        setStatus(status);
        setWealth(wealth);
        extent.add(this);
    }

    public Construction(String name, ConstructionType constructionType, LocalDate constructionDate, int localsNo, Location location, ConstructionStatus status, float wealth,String protection) {
        setName(name);
        setConstructionType(constructionType);
        setConstructionDate(constructionDate);
        setLocalsNo(localsNo);
        setLocation(location);
        setStatus(status);
        setWealth(wealth);
        addProtection(protection);
        extent.add(this);
    }

    public Construction(String name, ConstructionType constructionType, LocalDate constructionDate, int localsNo, LocalDate destructionDate, Location location, ConstructionStatus status, float wealth) {
        setName(name);
        setConstructionType(constructionType);
        setConstructionDate(constructionDate);
        setLocalsNo(localsNo);
        setLocation(location);
        setStatus(status);
        setWealth(wealth);
        setDestructionDate(destructionDate);
        extent.add(this);
    }

    /////////////////////////////////////GETTERS_AND_SETTERS//////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullValidationException("Construction name cannot be empty");
        }
        this.name = name;
    }

    public ConstructionType getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(ConstructionType constructionType) {
        if (constructionType == null) {
            throw new NullValidationException("Construction type cannot be empty");
        }
        if(status != null) {
            if(status != ConstructionStatus._DESTRUCTED_)
            {
                setStatus(ConstructionStatus._DESTRUCTED_);
            }
            setStatus(ConstructionStatus._STANDARD_);
        }

        this.constructionType = constructionType;
    }

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(LocalDate constructionDate) {
        if (constructionDate == null) {
            throw new NullValidationException("Construction date cannot be empty");
        }
        this.constructionDate = constructionDate;
        setDestructionDate(null);
    }

    public int getLocalsNo() {
        return localsNo;
    }

    public void setLocalsNo(int localsNo) {
        if (localsNo < 0) {
            throw new ContentViolationException("Number of local residents cannot be negative");
        }
        this.localsNo = localsNo;
    }

    public LocalDate getDestructionDate() {
        return destructionDate;
    }

    public void setDestructionDate(LocalDate destructionDate) {
        if (destructionDate != null && status != ConstructionStatus._DESTRUCTED_) {
            throw new ContentViolationException("Construction " + name + " status is not 'destructed' yet.");
        }
        this.destructionDate = destructionDate;
    }

    public List<String> getProtection() {
        return Collections.unmodifiableList(protection);
    }

    public void addProtection(String protection) {
        if (protection == null || protection.isBlank()) {
            throw new NullValidationException("Protection cannot be null");
        }
        this.protection.add(protection);
        addDiscoveredProtectionStructures(protection);
    }

    public void removeProtection(String protection) {
        if (protection == null || protection.isBlank()) {
            throw new NullValidationException("Protection cannot be null");
        }
        this.protection.remove(protection);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new NullValidationException("Location cannot be null");
        }
        this.location = location;
    }

    public Set<String> getDiscoveredResources() {
        return Collections.unmodifiableSet(discoveredResources);
    }

    public void addDiscoveredResources(String resource) {
        if (resource == null || resource.isBlank()) {
            throw new NullValidationException("Resource cannot be empty");
        }
        if (!location.getResources().contains(resource)) {
            throw new ContentViolationException("Resource with name " + resource + " cannot be added as it is not located in " + location);
        }
        discoveredResources.add(resource);
    }

    public void removeDiscoveredResources(String resource) {
        if (resource == null || resource.isBlank()) {
            throw new NullValidationException("Resource cannot be empty");
        }
        discoveredResources.remove(resource);
    }

    public ConstructionStatus getStatus() {
        return status;
    }

    public void setStatus(ConstructionStatus status) {
        if (status == null) {
            throw new NullValidationException("Construction status cannot be null");
        }
        if(this.status == ConstructionStatus._DESTRUCTED_ && status != ConstructionStatus._DESTRUCTED_) {
            LocalDate date = LocalDate.now(); //some date representing now
            setConstructionDate(date);
        }
        this.status = status;
        if(status == ConstructionStatus._DESTRUCTED_){
            LocalDate date = LocalDate.now(); //some date representing now
            destroyAllProtections();
            setDestructionDate(date);
        }
    }

    public float getWealth() {
        return wealth;
    }

    public void setWealth(float wealth) {
        this.wealth = wealth;
    }

    public float getProfit() {
        if (wealth <= 0) {
            return 0;
        }
        return wealth + (wealth / 2) * status.getIncomeIndicator();
    }

    public Period getAge() {
        LocalDate date = LocalDate.now(); //some date representing now
        return Period.between(constructionDate, date);
    }

    public static Set<String> getDiscoveredProtectionStructures() {
        return Collections.unmodifiableSet(discoveredProtectionStructures);
    }

    public static void addDiscoveredProtectionStructures(String protection) {
        if (protection == null || protection.isBlank()) {
            throw new NullValidationException("Protection cannot be null");
        }
        discoveredProtectionStructures.add(protection);
    }

    public static void removeDiscoveredProtectionStructures(String protection) {
        if (protection == null || protection.isBlank()) {
            throw new NullValidationException("Protection cannot be null");
        }
        discoveredProtectionStructures.remove(protection);
    }

    /////////////////////////////////////METHODS//////////////////////////////////////

    public void helpLocals(){
        ConstructionStatus[] statuses = ConstructionStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            if(statuses[i] == status) {
                if(i > 0){
                    setStatus(statuses[i-1]);
                }
                return;
            }
        }
    }

    public void discoverResourceType(){
        for (String res:location.getResources()) {
            if(!discoveredResources.contains(res))
            {
                discoveredResources.add(res);
                return;
            }
        }
    }

    public void raid(){
        ConstructionStatus[] statuses = ConstructionStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            if(statuses[i] == status) {
                if(i < statuses.length-1){
                    setStatus(statuses[i+1]);
                }
                return;
            }
        }
    }

    public void destroyAllProtections(){
        protection.clear();
    }

    public static List<Construction> findConstructionsByLocation(Location loc){
        if(loc == null){
            throw new NullValidationException("Location cannot be null");
        }
        return extent.stream()
                .filter(c -> c.getLocation() == loc)
                .collect(Collectors.toList());
    }

    public static List<Construction> findConstructionsByName(String name){
        if(name == null || name.isBlank()){
            throw new NullValidationException("Name cannot be empty");
        }
        return extent.stream()
                .filter(c -> c.getName().equals(name))
                .collect(Collectors.toList());
    }

    public static List<Construction> findConstructionsByType(ConstructionType type){
        if(type == null){
            throw new NullValidationException("Construction status cannot be empty");
        }
        return extent.stream()
                .filter(c -> c.getConstructionType() == type)
                .collect(Collectors.toList());
    }

    /////////////////////////////////////EXTENTS//////////////////////////////////////

    public static List<Construction> getExtent() {
        return extent;
    }

    public static void writeExtent(){
        File file = new File(DATA_DIR_PATH);
        file.mkdir();
        file = new File(DATA_DIR_PATH + DATA_FILE_NAME);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(extent);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readExtent(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_DIR_PATH + DATA_FILE_NAME)))
        {
            extent = (List<Construction>)ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        String prep = status.equals(ConstructionStatus._DESTRUCTED_)? "was":"is";
        return "\nThe " + constructionType + " of " + name + ", located in " + location + " was constructed on " + constructionDate +
                ". Number of local citizens "+ prep + " " + localsNo + ". The "+constructionType+" is now " + status + "." +
                (status.equals(ConstructionStatus._DESTRUCTED_)?"It was destructed on " + destructionDate+".":"") +
                "\nDiscovered resources:\n" + discoveredResources + "\nProtections\n" + protection;
    }
}
