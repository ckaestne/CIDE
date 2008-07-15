package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion_choices extends GenASTNode {
  public expansion_choices(ArrayList<expansion> expansion, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<expansion>("expansion", expansion)
    }, firstToken, lastToken);
  }
  public expansion_choices(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion_choices(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<expansion> getExpansion() {
    return ((PropertyList<expansion>)getProperty("expansion")).getValue();
  }
}
