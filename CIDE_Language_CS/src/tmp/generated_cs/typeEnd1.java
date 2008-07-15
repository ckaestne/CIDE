package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class typeEnd1 extends typeEnd {
  public typeEnd1(constructor_declaration constructor_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<constructor_declaration>("constructor_declaration", constructor_declaration)
    }, firstToken, lastToken);
  }
  public typeEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typeEnd1(cloneProperties(),firstToken,lastToken);
  }
  public constructor_declaration getConstructor_declaration() {
    return ((PropertyOne<constructor_declaration>)getProperty("constructor_declaration")).getValue();
  }
}
