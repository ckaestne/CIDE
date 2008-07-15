package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberValue2 extends MemberValue {
  public MemberValue2(MemberValueArrayInitializer memberValueArrayInitializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberValueArrayInitializer>("memberValueArrayInitializer", memberValueArrayInitializer)
    }, firstToken, lastToken);
  }
  public MemberValue2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberValue2(cloneProperties(),firstToken,lastToken);
  }
  public MemberValueArrayInitializer getMemberValueArrayInitializer() {
    return ((PropertyOne<MemberValueArrayInitializer>)getProperty("memberValueArrayInitializer")).getValue();
  }
}
