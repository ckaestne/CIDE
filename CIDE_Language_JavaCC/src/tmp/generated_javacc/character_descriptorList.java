package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class character_descriptorList extends GenASTNode {
  public character_descriptorList(ArrayList<character_descriptor> character_descriptor, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<character_descriptor>("character_descriptor", character_descriptor)
    }, firstToken, lastToken);
  }
  public character_descriptorList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new character_descriptorList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<character_descriptor> getCharacter_descriptor() {
    return ((PropertyList<character_descriptor>)getProperty("character_descriptor")).getValue();
  }
}
