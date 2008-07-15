package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_member_declarationEndType1 extends interface_member_declarationEndType {
  public interface_member_declarationEndType1(interface_indexer_declaration interface_indexer_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<interface_indexer_declaration>("interface_indexer_declaration", interface_indexer_declaration)
    }, firstToken, lastToken);
  }
  public interface_member_declarationEndType1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_member_declarationEndType1(cloneProperties(),firstToken,lastToken);
  }
  public interface_indexer_declaration getInterface_indexer_declaration() {
    return ((PropertyOne<interface_indexer_declaration>)getProperty("interface_indexer_declaration")).getValue();
  }
}
