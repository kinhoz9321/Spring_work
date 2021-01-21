package test.mypac;
/*
 * 기존에 사용하던 WeaponImpl 보다 좋은 기능의 무기라고 가정하자
 */
public class GoodWeapon implements Weapon{

	@Override
	public void attack() {
		System.out.println("조금 더 강력한 성능으로 공격해요!");
	}

}
