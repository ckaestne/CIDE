package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class character_list extends GenASTNode {
  public character_list(ASTTextNode text480, character_descriptorList character_descriptorList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text480", text480),
      new PropertyZeroOrOne<character_descriptorList>("character_descriptorList", character_descriptorList)
    }, firstToken, lastToken);
  }
  public character_list(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new character_list(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText480() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text480")).getValue();
  }
  public character_descriptorList getCharacter_descriptorList() {
    return ((PropertyZeroOrOne<character_descriptorList>)getProperty("character_descriptorList")).getValue();
  }
}
