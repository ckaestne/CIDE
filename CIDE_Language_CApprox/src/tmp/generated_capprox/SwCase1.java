package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SwCase1 extends SwCase {
  public SwCase1(Sequence_CodeUnit_InBlock sequence_CodeUnit_InBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Sequence_CodeUnit_InBlock>("sequence_CodeUnit_InBlock", sequence_CodeUnit_InBlock)
    }, firstToken, lastToken);
  }
  public SwCase1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SwCase1(cloneProperties(),firstToken,lastToken);
  }
  public Sequence_CodeUnit_InBlock getSequence_CodeUnit_InBlock() {
    return ((PropertyOne<Sequence_CodeUnit_InBlock>)getProperty("sequence_CodeUnit_InBlock")).getValue();
  }
}
