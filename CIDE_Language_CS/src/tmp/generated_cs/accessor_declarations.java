package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class accessor_declarations extends GenASTNode {
  public accessor_declarations(accessor_declaration accessor_declaration, accessor_declaration accessor_declaration1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<accessor_declaration>("accessor_declaration", accessor_declaration),
      new PropertyZeroOrOne<accessor_declaration>("accessor_declaration1", accessor_declaration1)
    }, firstToken, lastToken);
  }
  public accessor_declarations(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new accessor_declarations(cloneProperties(),firstToken,lastToken);
  }
  public accessor_declaration getAccessor_declaration() {
    return ((PropertyOne<accessor_declaration>)getProperty("accessor_declaration")).getValue();
  }
  public accessor_declaration getAccessor_declaration1() {
    return ((PropertyZeroOrOne<accessor_declaration>)getProperty("accessor_declaration1")).getValue();
  }
}
