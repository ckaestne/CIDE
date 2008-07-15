package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_member_declarationEnd2 extends interface_member_declarationEnd {
  public interface_member_declarationEnd2(type type, interface_member_declarationEndType interface_member_declarationEndType, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<interface_member_declarationEndType>("interface_member_declarationEndType", interface_member_declarationEndType)
    }, firstToken, lastToken);
  }
  public interface_member_declarationEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_member_declarationEnd2(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public interface_member_declarationEndType getInterface_member_declarationEndType() {
    return ((PropertyOne<interface_member_declarationEndType>)getProperty("interface_member_declarationEndType")).getValue();
  }
}
