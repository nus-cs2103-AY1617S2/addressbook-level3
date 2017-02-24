package seedu.addressbook.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

public abstract class Storage{
	protected String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";
	protected AddressBook addressBook;
	protected Path path;
	protected JAXBContext jaxbContext;
	public Storage(String DEFAULT_STORAGE_FILEPATH, AddressBook addressBook, JAXBContext jaxbContext){
		this.DEFAULT_STORAGE_FILEPATH = DEFAULT_STORAGE_FILEPATH;
		this.addressBook = addressBook;
		this.jaxbContext = jaxbContext;
	}
	protected Storage(){
	}
	public void setStorage(String DEFAULT_STORAGE_FILEPATH){
		this.DEFAULT_STORAGE_FILEPATH = DEFAULT_STORAGE_FILEPATH;
	}
	public String getStorage(){
		return DEFAULT_STORAGE_FILEPATH;
	}
	public AddressBook getAddressBook(){
		return addressBook;
	}
	public AddressBook load() throws StorageOperationException {
        try (final Reader fileReader =
                     new BufferedReader(new FileReader(path.toFile()))) {

            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final AdaptedAddressBook loaded = (AdaptedAddressBook) unmarshaller.unmarshal(fileReader);
            // manual check for missing elements
            if (loaded.isAnyRequiredFieldMissing()) {
                throw new StorageOperationException("File data missing some elements");
            }
            return loaded.toModelType();

        /* Note: Here, we are using an exception to create the file if it is missing. However, we should minimize
         * using exceptions to facilitate normal paths of execution. If we consider the missing file as a 'normal'
         * situation (i.e. not truly exceptional) we should not use an exception to handle it.
         */

        // create empty file if not found
        } catch (FileNotFoundException fnfe) {
            final AddressBook empty = new AddressBook();
            save(empty);
            return empty;

        // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (JAXBException jaxbe) {
            throw new StorageOperationException("Error parsing file data format");
        } catch (IllegalValueException ive) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }
	public void save(AddressBook addressBook) throws StorageOperationException {

        /* Note: Note the 'try with resource' statement below.
         * More info: https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
         */
        try (final Writer fileWriter =
                     new BufferedWriter(new FileWriter(path.toFile()))) {

            final AdaptedAddressBook toSave = new AdaptedAddressBook(addressBook);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(toSave, fileWriter);

        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path + " error: " + ioe.getMessage());
        } catch (JAXBException jaxbe) {
            throw new StorageOperationException("Error converting address book into storage format");
        }
    }
	public String getPath() {
		return path.toString();
	}
}