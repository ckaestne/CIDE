package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_member_modifier2 extends interface_member_modifier {
  public interface_member_modifier2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public interface_member_modifier2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_member_modifier2(cloneProperties(),firstToken,lastToken);
  }
}
