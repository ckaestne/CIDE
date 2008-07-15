package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class property_declaration extends GenASTNode {
  public property_declaration(accessor_declarations accessor_declarations, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<accessor_declarations>("accessor_declarations", accessor_declarations)
    }, firstToken, lastToken);
  }
  public property_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new property_declaration(cloneProperties(),firstToken,lastToken);
  }
  public accessor_declarations getAccessor_declarations() {
    return ((PropertyOne<accessor_declarations>)getProperty("accessor_declarations")).getValue();
  }
}
