package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class typeEnd5 extends typeEnd {
  public typeEnd5(type_name type_name, type_nameEnd type_nameEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type_name>("type_name", type_name),
      new PropertyOne<type_nameEnd>("type_nameEnd", type_nameEnd)
    }, firstToken, lastToken);
  }
  public typeEnd5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typeEnd5(cloneProperties(),firstToken,lastToken);
  }
  public type_name getType_name() {
    return ((PropertyOne<type_name>)getProperty("type_name")).getValue();
  }
  public type_nameEnd getType_nameEnd() {
    return ((PropertyOne<type_nameEnd>)getProperty("type_nameEnd")).getValue();
  }
}
